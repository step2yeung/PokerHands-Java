# Welcome!

Thank you for interviewing at iTrellis! We hope you find this process
to be fair, transparent, and understandable. Our goal with this
process is to evaluate your skillset, with no "surprises" or "trick
questions" designed to snare the unwary. If you have any questions
about this project or its purpose in the process, please feel free
to email us.

Our goal is not to extract free work out of you with this project;
please spend no more than 10 hours on this project. We believe that
any developer claiming to be a "journeyman" developer can complete
this in that time, but we also understand that certain developers
do some things faster than others.

If you are not familiar with Mercurial (hg), the source control
system used for this project, it is a distributed version control
system (DVCS) similar in concept to git. Numerous tutorials on
Mercurial are available on the Web, but the key commands that you
will need are:

  * "hg clone": To create a clone of the repository.
  * "hg branch": To create a branch for your changes
  * "hg add": To add files to the local working repo
  * "hg commit": To commit local working changes

BitBucket also has a [BitBucket101](https://confluence.atlassian.com/display/BITBUCKET/Bitbucket+101) for
those who want to have a look.

Please fork a copy of our project and create your changes as a
pull request.

You are also welcome to take a copy of the repo (see the download
link in the BitBucket menu to the right) and host it in your own
source-control system (a la Github), so long as we can get access
to it and view the repository history.


# PokerHands (Java)

The project goals are simple: We would like for you to use Java to
create a Web API that determines the winner of a round of poker.

A poker deck contains 52 cards. Each card has a suit of either
clubs, diamonds, hearts, or spades. Each card also has a value of either
2 through 10, jack, queen, king, or ace. For scoring purposes card values
are ordered as above, with 2 having the lowest and ace the highest value.
The suit has no impact on value.

A poker hand consists of five cards dealt from the deck. Poker hands are
ranked by the following partial order from lowest to highest:

* High Card: Hands which do not fit any higher category are ranked by the value of their highest card. If the highest cards have the same value, the hands are ranked by the next highest, and so on.
* Pair: Two of the five cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
* Two Pairs: The hand contains two different pairs. Hands which both contain two pairs are ranked by the value of their highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
* Three of a Kind. Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the value of the three cards.
* Straight. Hand contains five cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
* Flush. Hand contains five cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
* Full House. Three cards of the same value, with the remaining two cards forming a pair. Ranked by the value of the three cards.
* Four of a Kind. Four cards with the same value. Ranked by the value of the four cards.
* Straight Flush. Five cards of the same suit with consecutive values. Ranked by the highest card in the hand.

Your job is to compare several a pair of poker hands and to indicate which, if either, has a higher rank.

The input will consist of a JSON document like the following:

		{
			"hands": [
				{
					"name": "Ted",
					"hand": [ "2H", "3D", "5S", "9C", "KD" ]
				},
				{
					"name": "Louis",
					"hand": [ "2C", "3H", "4S", "8C", "AH" ]
				}
			]
		}

or

		{
			"hands": [
				{
					"name": "Black",
					"hand": [ "2H", "4S", "4C", "2D", "4H" ]
				},
				{
					"name": "White",
					"hand": [ "2S", "8S", "AS", "QS", "3S" ]
				}
			]
		}


or an XML document like the following:

		<hands>
			<hand>
				<name>Ted</name>
				<card>2H</card>
				<card>3D</card>
				<card>5S</card>
				<card>9C</card>
				<card>KD</card>
			</hand>
			<hand>
				<name>Louis</name>
				<card>2C</card>
				<card>3H</card>
				<card>4S</card>
				<card>8C</card>
				<card>AH</card>
			</hand>
		</hands>

or

		<hands>
			<hand>
				<name>Black</name>
				<card>2H</card>
				<card>4S</card>
				<card>4C</card>
				<card>2D</card>
				<card>4H</card>
			</hand>
			<hand>
				<name>White</name>
				<card>2S</card>
				<card>8S</card>
				<card>AS</card>
				<card>QS</card>
				<card>3S</card>
			</hand>
		</hands>

The result will be a single message indicating which player's hand is the victorious
one, suitable for display. The output will look like a JSON document like the following:

		{
			"result": "Ted wins"
		}

in the first case, or

		{
			"result": "White wins"
		}

in the second, or an XML like the following:

		<results>
			<result>Ted wins</result>
		</results>

or the second example:

		<results>
			<result>White wins</result>
		</results>

There should be just one endpoint, "/poker".

You are free to use whatever middleware, server, or other Java libraries
you think are useful and/or relevant, so long as they are present in
the build.

# Our goals

We have a few things we are looking for as part of this evaluation:

1. We don't care if you implement all of the stories above. We are
using this to evaluate your technical skills, not build a shippable
product. However, the more stories completed, the better, because
obviously we care to know that you can deliver to a deadline.

2. We don't care about user interface or UX in this project. We
are interviewing you as a Java Journeyman, not a Web Designer.

3. We are interested in things like code clarity and consistency.
We should be able to read the code and figure out what's going on,
but we don't want you to drown us in comments, either.

4. Unit tests are important to us.

5. Part of the reason we want you to build this project is because
in the next phase of the process, we are going to ask you to pair
with one of our developers to implement a new feature in the project,
and we feel it's only fair that you work from a codebase that you
find familiar to do it. New features could be something like
"make the endpoint support both XML and JSON by using an endpoint 'suffix' to
indicate which format to use (poker.xml for XML, poker.json for JSON)", or
"support more than two hands in each request", or "include the rank as part
of each result, so users can know what the relevant rankings are".

# Feedback

Part of our goal with this process is to be transparent. We believe
that we can always improve, and this process is no exception. We would
love to hear your thoughts about the process (whether we hire you or
not!), and invite you to email us at hr@itrellis.com to give us your
thoughts.

We look forward to seeing your work, and talking with you about
this codebase, our combined future, and working together!
